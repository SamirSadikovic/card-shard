import appAxios from "./appAxios";
import { Deck, DeckRequest } from "../utils/types";


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

 const createDeck = async (deckRequest: DeckRequest): Promise<Deck> => {
    return appAxios.post(`/decks/create`, deckRequest, {
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

const updateDeck = async (deck: Deck): Promise<Deck> => {
    const deckId = deck.id;
    return appAxios.put(`/decks/${deckId}`, deck, {
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

const deleteDeck = async (deckId: string): Promise<Deck> => {
    return appAxios.delete(`/decks/${deckId}`, {
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


export default { getDecksByUserId, getDeckById, createDeck, updateDeck, deleteDeck };