import { Handler } from 'express';
import UserSchema from '../../models/UserSchema';
import { User } from '../../types/User';
import mongoose from 'mongoose'; 

export const toggleUserHandler: Handler = async (req, res) => {
  const { userId } = req;
  const { name } = req.body;

  try {
     const existingUser = await UserSchema.findById(userId);

      if (!existingUser) {
      return res.status(404).send('User not found');
    }

    existingUser.displayname = name;
    await existingUser.save();
  

    return res.status(201).send();
  } catch (err) {
    console.error(err);
    return res.status(500).send();
  }
};
