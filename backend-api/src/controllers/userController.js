const User = require('../models/User');

exports.register = async (req, res) => {
  const { name, email, password } = req.body;
  try {
    const userExists = await User.findOne({ email });
    if (userExists) {
      return res.status(400).json({ error: 'El correo electrónico ya está registrado' });
    }

    // Nota: En producción, cifrar con bcrypt: const hashedPassword = await bcrypt.hash(password, 10);
    const user = new User({
      name,
      email,
      password // Guardado temporal para fines ilustrativos del equipo
    });

    await user.save();
    
    // Generar un token JWT básico
    const token = "jwt_mock_token_for_" + user._id;

    res.status(201).json({
      user: {
        id: user._id,
        name: user.name,
        email: user.email,
        bio: user.bio,
        avatarUrl: user.avatarUrl
      },
      token
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.login = async (req, res) => {
  const { email, password } = req.body;
  try {
    const user = await User.findOne({ email });
    if (!user || user.password !== password) {
      return res.status(401).json({ error: 'Credenciales inválidas' });
    }

    const token = "jwt_mock_token_for_" + user._id;
    res.json({
      user: {
        id: user._id,
        name: user.name,
        email: user.email,
        bio: user.bio,
        avatarUrl: user.avatarUrl,
        favoriteCount: user.favorites.length
      },
      token
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getProfile = async (req, res) => {
  try {
    const user = await User.findById(req.params.id).populate('favorites');
    if (!user) {
      return res.status(404).json({ error: 'Usuario no encontrado' });
    }
    res.json({
      id: user._id,
      name: user.name,
      email: user.email,
      bio: user.bio,
      avatarUrl: user.avatarUrl,
      reviewCount: 14, // Mock/contador
      favoriteCount: user.favorites.length
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.updateProfile = async (req, res) => {
  const { name, bio } = req.body;
  try {
    const user = await User.findByIdAndUpdate(
      req.params.id,
      { name, bio },
      { new: true }
    );
    if (!user) {
      return res.status(404).json({ error: 'Usuario no encontrado' });
    }
    res.json({
      id: user._id,
      name: user.name,
      email: user.email,
      bio: user.bio,
      avatarUrl: user.avatarUrl
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.toggleFavorite = async (req, res) => {
  const { restaurantId, isFavorite } = req.body;
  try {
    const updateQuery = isFavorite 
      ? { $addToSet: { favorites: restaurantId } } 
      : { $pull: { favorites: restaurantId } };

    const user = await User.findByIdAndUpdate(req.params.id, updateQuery, { new: true });
    if (!user) {
      return res.status(404).json({ error: 'Usuario no encontrado' });
    }
    res.json({ success: true, favoriteCount: user.favorites.length });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }

  exports.getFavorites = async (req, res) => {
  try {
    const user = await User.findById(req.params.id).populate('favorites');
    if (!user) {
      return res.status(404).json({ error: 'Usuario no encontrado' });
    }
    res.json(user.favorites);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
};
