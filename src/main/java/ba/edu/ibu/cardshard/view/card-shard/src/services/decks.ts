import appAxios from "./appAxios";
import { Deck } from "../utils/types";


const getDecksByUserId = async (userId: string): Promise<Deck[]> => {
   return appAxios.get(`/decks/user/${userId}`, { 'headers': { Authorization: 'Bearer ' + localStorage.getItem('userToken') } }).then(
       (response) => {
           const data = response.data;
           console.log(data);

           return data;
       });
}

const getDeckById = async (deckId: string): Promise<Deck> => {
    return appAxios.get(`/decks/${deckId}`, { 'headers': { Authorization: 'Bearer ' + localStorage.getItem('userToken') } }).then(
        (response) => {
            const data = response.data;
            console.log(data);
 
            return data;
        });
 }


export default { getDecksByUserId, getDeckById };