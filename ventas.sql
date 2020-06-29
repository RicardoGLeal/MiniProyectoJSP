-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-06-2020 a las 05:47:28
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `sinopsis` varchar(100) NOT NULL,
  `año` year(4) NOT NULL,
  `editorial` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `año` int(11) DEFAULT NULL,
  `categoria` varchar(50) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `recaudacion` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `userid` int(11) NOT NULL,
  `usuario` varchar(40) NOT NULL,
  `password` varchar(56) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`userid`, `usuario`, `password`) VALUES
(1, 'coco', '12345'),
(44, 'coco', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_libro`
--

CREATE TABLE `venta_libro` (
  `id` int(11) NOT NULL,
  `libroid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_pelicula`
--

CREATE TABLE `venta_pelicula` (
  `id` int(11) NOT NULL,
  `peliculaid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_videojuego`
--

CREATE TABLE `venta_videojuego` (
  `id` int(11) NOT NULL,
  `videojuegoid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuego`
--

CREATE TABLE `videojuego` (
  `id` int(11) NOT NULL,
  `titulo` varchar(60) NOT NULL,
  `desarrollador` varchar(50) NOT NULL,
  `distribuidora` varchar(50) NOT NULL,
  `clasificacion` varchar(20) NOT NULL,
  `año` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`userid`);

--
-- Indices de la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `libroid` (`libroid`),
  ADD KEY `userid` (`userid`);

--
-- Indices de la tabla `venta_pelicula`
--
ALTER TABLE `venta_pelicula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `peliculaid` (`peliculaid`),
  ADD KEY `userid` (`userid`);

--
-- Indices de la tabla `venta_videojuego`
--
ALTER TABLE `venta_videojuego`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`),
  ADD KEY `videojuegoid` (`videojuegoid`);

--
-- Indices de la tabla `videojuego`
--
ALTER TABLE `videojuego`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `venta_pelicula`
--
ALTER TABLE `venta_pelicula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `venta_videojuego`
--
ALTER TABLE `venta_videojuego`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `videojuego`
--
ALTER TABLE `videojuego`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  ADD CONSTRAINT `venta_libro_ibfk_1` FOREIGN KEY (`libroid`) REFERENCES `libro` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_libro_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `usuario` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta_pelicula`
--
ALTER TABLE `venta_pelicula`
  ADD CONSTRAINT `venta_pelicula_ibfk_1` FOREIGN KEY (`peliculaid`) REFERENCES `pelicula` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_pelicula_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `usuario` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta_videojuego`
--
ALTER TABLE `venta_videojuego`
  ADD CONSTRAINT `venta_videojuego_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `usuario` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_videojuego_ibfk_2` FOREIGN KEY (`videojuegoid`) REFERENCES `videojuego` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
