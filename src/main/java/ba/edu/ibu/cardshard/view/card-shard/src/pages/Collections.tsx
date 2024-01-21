import TagView from "../components/TagView"
import CollectionBrowser from "../components/CollectionBrowser"
import useCollections from "../hooks/useCollections"
import useCurrentUser from "../hooks/useCurrentUser"
import { CollectedCard } from "../utils/types"
import useRemoveCard from "../hooks/useRemoveCard"
import { useState } from "react"

const Collections = () => {
  const user = useCurrentUser();
  const { data: collection, isLoading, isError } = useCollections(user.data?.id!);
  const [activeTags, setActiveTags] = useState<string[]>([]);

  const removeCard = useRemoveCard(collection?.id!);
  console.log(isLoading);
  console.log(isError);
  console.log(collection);
  
  const deleteCollectedCard = (card: CollectedCard) => {
    removeCard.mutate(card, {
      onError: () => {
        <div className="row">
          <div className="col-12 col-md-3 m-3">
            <div className="alert alert-danger" role="alert">
                <p className="mb-0">
                Something went wrong, please try again.
                </p>
            </div>
          </div>
        </div>
      }
    });
  }

  return (
    <div>
      {
        isLoading && !collection &&
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      }
      {
        isError &&
        <div className="row">
          <div className="col-12 col-md-3 m-3">
            <div className="alert alert-danger" role="alert">
              <p className="mb-0">
                Something went wrong, please try again.
              </p>
            </div>
          </div>
        </div>
      }
      {
        !isLoading && collection &&
        <div className="row m-5">
          <div className="col-lg-2">
            <TagView
              collection={collection}
              tagChange={setActiveTags}
            />
          </div>
          <div className="col-lg-10">
            <CollectionBrowser
              collection={collection}
              activeTags={activeTags}
              onDeleteClick={deleteCollectedCard}
            />
          </div>
        </div>
      }
  </div>
  )
}


export default Collections