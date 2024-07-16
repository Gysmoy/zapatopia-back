-- cargar estados

INSERT INTO estados (id, estado, descripcion, fecha_creacion, fecha_modificacion) VALUES  (1, 'PENDIENTE', 'El pedido ha sido recibido pero aún no ha sido procesado.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO estados (id, estado, descripcion, fecha_creacion, fecha_modificacion) VALUES  (2, 'PROCESADO', 'El pedido está siendo preparado para el envío.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO estados (id, estado, descripcion, fecha_creacion, fecha_modificacion) VALUES  (3, 'ENTREGADO', 'El pedido ha sido entregado al cliente.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)


--cargar categorias

insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (1, 'trialrunning', 'Trial Running', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (2, 'lifestyle', 'Lifestyle', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (3, 'tenis', 'Tenis', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (4, 'futbol', 'Futbol', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (5, 'baloncesto', 'Baloncesto', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (6, 'crossfit', 'Crossfit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (7, 'senderismo', 'Senderismo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into categorias (id, categoria, descripcion, fecha_creacion, fecha_modificacion) values (8, 'sandalias', 'Sandalias', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

-- carga marca
insert into marcas (id, marca, descripcion, fecha_creacion, fecha_modificacion) values (1, 'Nike', 'Nike', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into marcas (id, marca, descripcion, fecha_creacion, fecha_modificacion) values (2, 'Adidas', 'Adidas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into marcas (id, marca, descripcion, fecha_creacion, fecha_modificacion) values (3, 'Puma', 'Puma', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into marcas (id, marca, descripcion, fecha_creacion, fecha_modificacion) values (4, 'Converse', 'Converse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into marcas (id, marca, descripcion, fecha_creacion, fecha_modificacion) values (5, 'Vans', 'Vans', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

-- cargar almacenes
INSERT INTO almacenes (id, almacen, direccion, fecha_creacion, fecha_modificacion) VALUES (1, 'Almacen Central', 'Av. Principal 123, Lima', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO almacenes (id, almacen, direccion, fecha_creacion, fecha_modificacion) VALUES (2, 'Almacen Sur', 'Calle Secundaria 456, Arequipa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO almacenes (id, almacen, direccion, fecha_creacion, fecha_modificacion) VALUES (3, 'Almacen Norte', 'Jr. Tercero 789, Trujillo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO almacenes (id, almacen, direccion, fecha_creacion, fecha_modificacion) VALUES (4, 'Almacen Este', 'Pasaje Cuarto 101, Cusco', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO almacenes (id, almacen, direccion, fecha_creacion, fecha_modificacion) VALUES (5, 'Almacen Oeste', 'Av. Quinto 202, Piura', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

-- cargar productos
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (1, 1, 1, 'Nike Air Force', 'El fulgor vive en Nike Air Force 1 ’07, el ícono del básquetbol que le da un toque fresco a las características más recordadas: colores audaces y la cantidad perfecta de destellos para que brilles.', 450, 40, 'N', 'M', '/images/1/nike.webp', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (2, 2, 2, 'ZAPATILLAS ADIDAS GRAND COURT CLOUDFOAM COMFORT', 'Para pies delgados recomendamos comprar la talla inferior. Revisa la equivalencia, H: Hombre | M: Mujer. Si este artículo es personalizado, no aplica en nuestra política de cambios y devoluciones.', 320, 41, 'N', 'M', '/images/2/adidas.avif', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (3, 3, 3, 'ZAPATILLAS URBANAS PUMA PARA HOMBRE PUMA-180', 'Las PUMA-180 recurren a la estética skate de los años 90, actualizada para la actual generación de skaters. Estas zapatillas presentan un diseño robusto, un acolchado grueso y un discreto exterior técnico.', 380, 42, 'N', 'U', '/images/3/puma.jpg', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (4, 4, 4, 'Chuck Taylor All Star Lift Platform Sparkle Party', 'EL ALMA DE LA FIESTA Con estas zapatillas con plataforma en tu colección, seguro que encuentras una excusa para celebrar. Este legendario modelo de corte bajo está diseñado para captar todas las miradas, con un recubrimiento de brillo por toda la superficie y una suela de plataforma apilada.', 410, 43, 'R', 'U', '/images/4/converse.webp', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (5, 5, 5, 'ZAPATILLAS VANS OLD SKOOL - NAVY', 'El modelo Old Skool, es un zapato clasico de skate y es el primer modelo en llevar la iunica franja lateral, su dise??o de perfil bajo con amarre de pasadores cuenta con una parte superior de cuero y lona, con una lengueta y forro acolchados, ademas de la muy reconocida suela de goma con forma de Waffle.', 290, 39, 'A', 'F', '/images/5/vans.webp', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (6, 6, 1, 'Nike Air Max Dn', 'Presentamos la nueva generación de tecnología Air. Las Air Max Dn cuentan con nuestro sistema de unidades Dynamic Air de tubos de presión dual para ofrecer reactividad en cada pisada. El resultado es un diseño futurista lo suficientemente cómodo para llevarlo de día o de noche.', 470, 43, 'R', 'U', '/images/6/nike.png', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (7, 7, 2, 'Zapatillas Urbanas Hombre Adidas Originals Forum Mid', 'Las zapatillas adidas Forum han dominado las canchas de básquet y las calles, y ahora regresan con una versión de corte medio para llevar tus pasos a otro nivel. Envuelve tus pies con un estilo inconfundible en piel revestida prémium para un look que exude clase', 360, 44, 'B', 'M', '/images/7/adidas.avif', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into productos (id, categoria_id, marca_id, nombre, descripcion, precio_compra, talla, color, genero, path_foto, stock_general, fecha_creacion, fecha_modificacion) values (8, 8, 3, 'Zapatilla Walking Mujer Carina 2.0 Mid', 'Las zapatillas Carina 2.0 toman una silueta inspirada en el tenis y le dan un cambio de imagen listo para la playa de California, todo con una generosa dosis de puro estilo de los años 80. Están listos para tomar las calles, y la arena, por asalto, garantizando vibraciones relajadas con cada paso que das.', 420, 43, 'R', 'U', '/images/8/puma.jpg', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

-- cargar stock
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (1, 1, 1, 100, 450, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (2, 1, 2, 100, 320, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (3, 1, 3, 100, 380, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (4, 1, 4, 100, 410, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (5, 2, 5, 100, 290, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (6, 3, 6, 100, 470, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (7, 4, 7, 100, 360, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into stock (id, almacen_id, producto_id, cantidad, precio_venta, fecha_creacion, fecha_modificacion) values (8, 5, 8, 100, 420, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)