import { useQuery } from "react-query";
import { CardService } from "../services";

const useCardsByIds = (cardIds: number[]) => {
    return useQuery(['cardsByIds', cardIds],
        () => CardService.getCardsByIds(cardIds), {
            enabled: cardIds != undefined,
            onError: () => {
                return [];
            }
        }
    );
}


export default useCardsByIds;