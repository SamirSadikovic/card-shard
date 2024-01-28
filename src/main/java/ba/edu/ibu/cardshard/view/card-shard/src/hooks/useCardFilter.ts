import { useQuery } from "react-query";
import { CardService } from "../services";
import { CardFilterFormData } from "../components/CardSearchForm/CardSearchForm";

const useCardFilter = (cardFilterParams: CardFilterFormData, pageNumber: number, cardsPerPage: number) => {
    return useQuery(['cardFilter', cardFilterParams, pageNumber, cardsPerPage],
        () => CardService.filterCards(cardFilterParams, pageNumber, cardsPerPage), {
            enabled: cardFilterParams != undefined
        }
    );
}


export default useCardFilter;