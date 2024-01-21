import appAxios from "./appAxios";
import { CollectedCard, Collection } from "../utils/types";

const getCollectionByUserId = async (userId: string): Promise<Collection> => {
    return appAxios.get(`/collections/user/${userId}/collection`, { 'headers': { Authorization: 'Bearer ' + localStorage.getItem('userToken') } }).then(
        (response) => {
            const data = response.data;
            console.log(data);

            return data;
        });
}

const getTagsByCollectionId = async (collectionId: string): Promise<string[]> => {
    return appAxios.get(`/collections/${collectionId}/tags`, { 'headers': { Authorization: 'Bearer ' + localStorage.getItem('userToken') } }).then(
        (response) => {
            const data = response.data;
            console.log(data);
 
            return data;
        });
}

const addCard = async (collectionId: string, collectedCard: CollectedCard): Promise<Collection> => {
    return appAxios.put(`/collections/${collectionId}/cards/add`, collectedCard, {
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

const removeCard = async (collectionId: string, collectedCard: CollectedCard): Promise<Collection> => {
    return appAxios.put(`/collections/${collectionId}/cards/remove`, collectedCard, {
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

const updateCollection = async (collection: Collection): Promise<Collection> => {
    const collectionId = collection.id;
    return appAxios.put(`/collections/${collectionId}`, collection, {
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


export default { getCollectionByUserId, getTagsByCollectionId, addCard, removeCard, updateCollection };

