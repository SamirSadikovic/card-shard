import { useMutation, useQueryClient } from 'react-query';
import { CollectionService } from '../services';
import { CollectedCard } from '../utils/types';


const useRemoveCard = (collectionId: string) => {
   const queryClient = useQueryClient();
   return useMutation((data: CollectedCard) => CollectionService.removeCard(collectionId, data), {
       onSuccess: () => {
            queryClient.invalidateQueries('cardsByIds', { exact: true });
            queryClient.invalidateQueries('collection', { exact: true });
       }
   });
};


export default useRemoveCard;