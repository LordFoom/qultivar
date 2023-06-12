import axios from 'axios';

export const fetchUserId = async (email, token) => {
    try {
        const userCredentials = await axios.get(`/api/v1/user/email/${email}`, {
            headers: { Authorization: `Bearer ${token}` },
        });
        return userCredentials.data.id;
    } catch (error) {
        console.log(error);
        return null; // Return null or handle the error as per your requirement
    }
};