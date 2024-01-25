import { useMutation, useQueryClient } from 'react-query';
import { DeckService } from '../services';
import { DeckRequest } from '../utils/types';


const useCreateDeck = () => {
   const queryClient = useQueryClient();
   return useMutation((data: DeckRequest) => DeckService.createDeck(data), {
       onSuccess: () => {
            queryClient.refetchQueries('decksByUserId', { exact: true });
       }
   });
};


export default useCreateDeck;