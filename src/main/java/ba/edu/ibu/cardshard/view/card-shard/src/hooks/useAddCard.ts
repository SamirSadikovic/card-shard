import { useMutation, useQueryClient } from 'react-query';
import { CollectionService } from '../services';
import { CollectedCard } from '../utils/types';


const useAddCard = (collectionId: string) => {
   const queryClient = useQueryClient();
   return useMutation((data: CollectedCard) => CollectionService.addCard(collectionId, data), {
       onSuccess: () => {
            queryClient.refetchQueries('cardsByIds', { exact: true });
            queryClient.refetchQueries('collection', { exact: true });
       }
   });
};


export default useAddCard;