import TagView from "../components/TagView"
import CollectionBrowser from "../components/CollectionBrowser"
import useCollections from "../hooks/useCollections"
import useCurrentUser from "../hooks/useCurrentUser"
import { CollectedCard } from "../utils/types"
import useRemoveCard from "../hooks/useRemoveCard"
import { useState } from "react"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"

const Collections = () => {
  const { data: user } = useCurrentUser();
  const { data: collection, isLoading, isError } = useCollections(user?.id!);
  const [activeTags, setActiveTags] = useState<string[]>([]);
  const [previewCard, setPreviewCard] = useState(defaultPreview);
  const [cardsPerPage, setCardsPerPage] = useState(10);

  const removeCard = useRemoveCard(collection?.id!);
  
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
          <div className="col-lg-8">
            <CollectionBrowser
              collection={collection}
              activeTags={activeTags}
              cardsPerPage={cardsPerPage}
              onPreviewClick={setPreviewCard}
              onDeleteClick={deleteCollectedCard}
            />
          </div>
          <div className="col-lg-2">
            <h3 className="text-center">Preview</h3>
            <hr/>
            <CardPreview
              card={previewCard? previewCard : defaultPreview}
            />
            <div className="row mt-1 mx-auto">
              <h5 className="col-12">Cards per page: </h5>
              <select className="form-control" onChange={(c) => setCardsPerPage(Number(c.target.value))}>
                <option>5</option>
                <option selected>10</option>
                <option>25</option>
                <option>50</option>
              </select>
            </div>
          </div>
        </div>
      }
  </div>
  )
}


export default Collections