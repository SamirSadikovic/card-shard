import { useQuery } from "react-query";
import { CollectionService } from "../services";

const useTags = (collectionId: string) => {
    return useQuery('tags',
        () => CollectionService.getTagsByCollectionId(collectionId), {
            enabled: collectionId != undefined
        }
    );
}


export default useTags;