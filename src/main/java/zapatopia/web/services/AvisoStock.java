const express = require('express');
const nodemailer = require('nodemailer');
const bodyParser = require('body-parser');

const app = express();
const port = 3000;

app.use(bodyParser.json());

// Aquí debo colocar base de datos de Zapatopia en memoria para almacenar las suscripciones
let subscriptions = {};

// Ruta para suscribirse
app.post('/subscribe', (req, res) => {
    const { productId, email } = req.body;

    if (!subscriptions[productId]) {
        subscriptions[productId] = [];
    }

    subscriptions[productId].push(email);
    res.json({ success: true });
});

// Función para enviar correos electrónicos
async function sendEmails(productId) {
    const emails = subscriptions[productId];

    if (!emails || emails.length === 0) return;

    let transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'tu_email@gmail.com',
            pass: 'tu_contraseña'
        }
    });

    let mailOptions = {
        from: 'tu_email@gmail.com',
        to: emails.join(','),
        subject: 'Producto en stock',
        text: `El producto con ID ${productId} está nuevamente en stock. ¡Compra ahora!`
    };

    try {
        await transporter.sendMail(mailOptions);
        console.log('Correos enviados exitosamente.');
    } catch (error) {
        console.error('Error al enviar correos:', error);
    }
}

// Simular la llegada de stock
app.post('/notify-stock', (req, res) => {
    const { productId } = req.body;
    sendEmails(productId);
    res.json({ success: true });
});

app.listen(port, () => {
    console.log(`Servidor escuchando en http://localhost:${port}`);
});
