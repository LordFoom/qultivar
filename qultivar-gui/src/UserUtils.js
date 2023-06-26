// UserUtils.js
import axios from 'axios';

export const fetchUserId = async (email, token) => {
    try {
        const userCredentials = await axios.get(`/api/v1/user/email/${email}`, {
            headers: { Authorization: `Bearer ${token}` },
        });
        return userCredentials.data.id;
    } catch (error) {
        throw new Error(error);
    }
};