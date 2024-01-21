import { ChangeEvent, useEffect, useLayoutEffect, useState } from "react";
import useTags from "../../hooks/useTags";
import { Collection } from "../../utils/types"

type Props = {
  collection: Collection,
  tagChange: (tags: string[]) => void;
}

const TagView = ({ collection, tagChange }: Props) => {
  const { data: tags, isLoading, refetch } = useTags(collection.id);
  const [activeTags, setActiveTags] = useState<string[]>([]);

  useLayoutEffect(() => {
    refetch();
    setActiveTags([]);
  }, [collection]);

  useEffect(() => {
    tagChange(activeTags);
  }, [activeTags]);

  const _tagChange = (tag: string, checked: boolean) => {
    var activeTagsUpdated = activeTags?.map(t => t);

    if(checked){
      activeTagsUpdated?.push(tag);
    } else {
      const index = activeTagsUpdated?.indexOf(tag, 0)!;
      activeTagsUpdated?.splice(index, 1);
    }
    console.log(activeTagsUpdated);
    setActiveTags(activeTagsUpdated);
  }

  return (
    <div className="container-sm container-tags">
      {
        isLoading && !tags &&
        <div className="container-fluid justify-content-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      }
      {
        !isLoading && tags &&
        <div>
          <h3 className="text-center">Tags</h3>
          <hr/>
          <form className="mb-2">
            {tags?.map((tag, index) => (
              <div className="form-check form-switch" key={ index }>
                <input className="form-check-input" type="checkbox" id={ tag } onChange={(e) => _tagChange(tag, e.target.checked)}/>
                <label className="form-check-label">{ tag }</label>
              </div>
            ))}
          </form>
          {(tags.length != 0) && <hr/>}
        </div>
      }
      {
        !isLoading && tags?.length == 0 &&
        <div className="row justify-content-center">
            <div className="col-12">
                <div className="alert" role="alert">
                    <p className="mb-0 text-center">
                        No tags
                    </p>
                </div>
            </div>
        </div>
      }
    </div>
  )
}


export default TagView
