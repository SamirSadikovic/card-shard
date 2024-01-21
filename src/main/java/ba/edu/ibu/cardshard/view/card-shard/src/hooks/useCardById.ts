import { useQuery } from "react-query";
import { CardService } from "../services";

const useCardById = (cardId: number) => {
    return useQuery('cardById',
        () => CardService.getCardById(cardId),
        {
            enabled: cardId != undefined
        }
    );
}


export default useCardById;