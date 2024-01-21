import { useQuery } from "react-query";
import { CardService } from "../services";
import { CardFilterFormData } from "../components/CardSearchForm/CardSearchForm";

const useCardFilter = (cardFilterParams: CardFilterFormData) => {
    return useQuery(['cardFilter', cardFilterParams],
        () => CardService.filterCards(cardFilterParams), {
            enabled: cardFilterParams != undefined
        }
    );
}


export default useCardFilter;