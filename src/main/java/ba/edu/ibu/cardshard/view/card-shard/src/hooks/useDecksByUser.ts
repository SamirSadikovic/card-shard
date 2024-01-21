import { useQuery } from "react-query";
import { DeckService } from "../services";

const useDecksByUser = (userId: string) => {
   return useQuery('decksByUserId',
       () => DeckService.getDecksByUserId(userId), {
            enabled: userId != undefined
       }
   );
}


export default useDecksByUser;