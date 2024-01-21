import appAxios from "./appAxios";
import { User } from "../utils/types";


const getUserById = async (userId: string): Promise<User> => {
   return appAxios.get(`/users/${userId}`, { 
        headers: { 
            Authorization: 'Bearer ' + localStorage.getItem('userToken')
        }
    }).then(
    (response) => {
        const data = response.data;
        console.log(data);

        return data;
    });
}

const getCurrentUser = async (): Promise<User> => {
    return appAxios.get(`/users/me`, {
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('userToken')
        },
        params: {
            jwt: localStorage.getItem('userToken')
        }
    }).then(
    (response) => {
        const data = response.data;
        console.log(data);

        return data;
    });
 }


export default { getUserById, getCurrentUser };