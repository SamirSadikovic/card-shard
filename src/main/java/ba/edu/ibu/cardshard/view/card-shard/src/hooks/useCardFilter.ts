import { useQuery } from "react-query";
import { CardService } from "../services";
import { CardFilterFormData } from "../components/CardSearchForm/CardSearchForm";

const useCardFilter = (cardFilterParams: CardFilterFormData, pageNumber: number) => {
    return useQuery(['cardFilter', cardFilterParams, pageNumber],
        () => CardService.filterCards(cardFilterParams, pageNumber), {
            enabled: cardFilterParams != undefined
        }
    );
}


export default useCardFilter;