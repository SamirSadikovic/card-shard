import { useMutation, useQueryClient } from 'react-query';
import { DeckService } from '../services';

const useDeleteDeck = (deckId: string) => {
   const queryClient = useQueryClient();
   return useMutation(() => DeckService.deleteDeck(deckId), {
       onSuccess: () => {
            queryClient.refetchQueries('decksByUserId', { exact: true });
        }
   });
};


export default useDeleteDeck;