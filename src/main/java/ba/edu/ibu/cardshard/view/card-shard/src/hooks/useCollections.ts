import { useQuery } from "react-query";
import { CollectionService } from "../services";

const useCollections = (userId: string) => {
   return useQuery('collection',
       () => CollectionService.getCollectionByUserId(userId), {
            enabled: userId != undefined
        }
   );
}


export default useCollections;