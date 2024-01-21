import { useQuery } from "react-query";
import { DeckService } from "../services";

const useDeckById = (deckId: string) => {
    return useQuery(['deckById', deckId],
        () => DeckService.getDeckById(deckId), {
            enabled: deckId != undefined
        }
    );
}


export default useDeckById;