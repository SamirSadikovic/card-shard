import { useMutation, useQueryClient } from 'react-query';
import { DeckService } from '../services';
import { Deck } from '../utils/types';


const useUpdateDeck = () => {
   const queryClient = useQueryClient();
   return useMutation((data: Deck) => DeckService.updateDeck(data), {
       onSuccess: () => {
            queryClient.refetchQueries('decksByUserId', { exact: true });
        }
   });
};


export default useUpdateDeck;