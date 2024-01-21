import { useMutation, useQueryClient } from 'react-query';
import { CollectionService } from '../services';
import { Collection } from '../utils/types';


const useUpdateCollection = () => {
   const queryClient = useQueryClient();
   return useMutation((data: Collection) => CollectionService.updateCollection(data), {
       onSuccess: () => {
           queryClient.invalidateQueries(['card']);
           queryClient.invalidateQueries('collection', { exact: true });
       }
   });
};


export default useUpdateCollection;