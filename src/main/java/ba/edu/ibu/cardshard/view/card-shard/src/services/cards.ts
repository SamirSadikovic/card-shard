import appAxios from "./appAxios";
import { Card } from "../utils/types";
import { CardFilterFormData } from "../components/CardSearchForm/CardSearchForm";


const getCards = async (): Promise<Card[]> => {
   return appAxios.get(`/cards`).then(
       (response) => {
           const data = response.data;
           console.log(data);

           return data;
       });
}

const filterCards = async (cardFilterParams: CardFilterFormData, pageNumber: number): Promise<Card[]> => {
    return appAxios.get(`/cards/filter/${pageNumber}`, {
        params: {
            text: cardFilterParams.text,
            type: cardFilterParams.type,
            race: cardFilterParams.race,
            monsterType: cardFilterParams.monsterType,
            monsterFrame: cardFilterParams.monsterFrame,
            attribute: cardFilterParams.attribute,
            levelRankLinkVal: cardFilterParams.levelRankLinkVal,
            scale: cardFilterParams.scale,
            atk: cardFilterParams.atk,
            def: cardFilterParams.def,
            linkMarkers: cardFilterParams.linkMarkers
        },
        paramsSerializer: {
            indexes: null
        }
    }).then(
    (response) => {
        const data = response.data;
        console.log(data);

        return data;
    });
}

const getCardsByIds = async (cardIds: number[]): Promise<Card[]> => {
    if(cardIds.length == 0){
        cardIds = [];
    }
    return appAxios.get('/cards/ids', {
        params: {
            ids: cardIds
        },
        paramsSerializer: {
            indexes: null
        }
    }).then(
    (response) => {
        const data = response.data;
        console.log(data);

        return data;
    });
}

const getCardById = async (cardId: number): Promise<Card> => {
    return appAxios.get(`/cards/${cardId}`).then(
    (response) => {
        const data = response.data;
        console.log(data);

        return data;
    });
}


export default { getCards, filterCards, getCardsByIds, getCardById };