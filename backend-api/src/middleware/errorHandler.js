// Middleware de manejo de errores global
const errorHandler = (err, req, res, next) => {
  console.error(err.stack);

  // Error de validación de Mongoose
  if (err.name === 'ValidationError') {
    const messages = Object.values(err.errors).map(e => e.message);
    return res.status(400).json({ error: messages.join(', ') });
  }

  // Error de ID inválido de MongoDB
  if (err.name === 'CastError') {
    return res.status(400).json({ error: 'ID inválido' });
  }

  // Error de campo único duplicado
  if (err.code === 11000) {
    return res.status(400).json({ error: 'El correo electrónico ya está registrado' });
  }

  // Error general
  res.status(500).json({ error: 'Error interno del servidor' });
};

module.exports = errorHandler;