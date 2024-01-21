import { useForm } from "react-hook-form";

type Props = {
  onSubmit: (data: CardFilterFormData) => void;
}

export type CardFilterFormData = {
  text: string,
  type: string,
  race: string,
  monsterType: string,
  monsterFrame: string,
  attribute: string,
  levelRankLinkVal: number,
  scale: number,
  atk: number,
  def: number,
  linkMarkers: any,
  pageNumber: number
}

const CardSearchForm = ({ onSubmit } : Props) => {
  const { register, handleSubmit } = useForm<CardFilterFormData>();
  
  const _onSubmit = (data: CardFilterFormData) => {
    const linkMarkerArray = [
      data.linkMarkers.topLeft ? "Top-Left" : null,
      data.linkMarkers.top ? "Top" : null,
      data.linkMarkers.topRight ? "Top-Right" : null,
      data.linkMarkers.left ? "Left" : null,
      data.linkMarkers.right ? "Right" : null,
      data.linkMarkers.bottomLeft ? "Bottom-Left" : null,
      data.linkMarkers.bottom ? "Bottom" : null,
      data.linkMarkers.bottomRight ? "Bottom-Right" : null,
    ].filter(lm => lm != null);

    if (linkMarkerArray.length == 0)
      linkMarkerArray[0] = "DEFAULT";

    const sendData = {
      text: data.text? data.text : "DEFAULT",
      type: data.type? data.type : "DEFAULT",
      race: data.race? data.race : "DEFAULT",
      monsterType: data.monsterType? data.monsterType : "DEFAULT",
      monsterFrame: data.monsterFrame? data.monsterFrame : "DEFAULT",
      attribute: data.attribute? data.attribute : "DEFAULT",
      levelRankLinkVal: data.levelRankLinkVal? data.levelRankLinkVal : -1,
      scale: data.scale? data.scale : -1,
      atk: data.atk? data.atk : -1,
      def: data.def? data.def : -1,
      linkMarkers: linkMarkerArray,
      pageNumber: data.pageNumber? data.pageNumber : 0
    }
    onSubmit(sendData);
  }

  return (
    <div className="container-sm card-search-form">
      <form onSubmit={handleSubmit(_onSubmit)}>
        <div className="row">
          <div className="col form-group">
            <label>Card text</label>
            <input type="text" className="form-control" {...register("text")}/>
          </div>
        </div>
        <div className="row">
          <div className="col form-group">
            <label>Card type</label>
            <select className="form-control" {...register("type")}>
              <option></option>
              <option>Monster</option>
              <option>Spell</option>
              <option>Trap</option>
            </select>
          </div>
        </div>
        <div className="row">
          <div className="col form-group">
            <label>Monster sublcasses</label>
          </div>
        </div>
        <div className="row">
          <div className="col form-group">
            <select className="form-control" {...register("monsterType")}>
              <option></option>
              <option>Normal</option>
              <option>Effect</option>
              <option>Flip</option>
              <option>Spirit</option>
              <option>Gemini</option>
              <option>Union</option>
              <option>Toon</option>
              <option>Tuner</option>
            </select>
          </div>
          <div className="col form-group">
            <select className="form-control" {...register("monsterFrame")}>
              <option></option>
              <option>Ritual</option>
              <option>Fusion</option>
              <option>Synchro</option>
              <option>XYZ</option>
              <option>Pendulum</option>
              <option>Link</option>
            </select>
          </div>
        </div>
        <div className="row">
          <div className="col form-group">
            <label>Type</label>
            <select className="form-control" {...register("race")}>
              <option></option>
              <optgroup label="Monster">
                <option>Aqua</option>
                <option>Beast</option>
                <option>Beast-Warrior</option>
                <option>Creator-God</option>
                <option>Cyberse</option>
                <option>Dinosaur</option>
                <option>Divine-Beast</option>
                <option>Dragon</option>
                <option>Fairy</option>
                <option>Fiend</option>
                <option>Fish</option>
                <option>Illusion</option>
                <option>Insect</option>
                <option>Machine</option>
                <option>Plant</option>
                <option>Psychic</option>
                <option>Pyro</option>
                <option>Reptile</option>
                <option>Rock</option>
                <option>Sea Serpent</option>
                <option>Spellcaster</option>
                <option>Thunder</option>
                <option>Warrior</option>
                <option>Winged Beast</option>
                <option>Wyrm</option>
                <option>Zombie</option>
              </optgroup>

              <optgroup label="Spell">
                <option>Normal</option>
                <option>Continuous</option>
                <option>Equip</option>
                <option>Quick-Play</option>
                <option>Field</option>
                <option>Ritual</option>
              </optgroup>

              <optgroup label="Trap">
                <option>Normal</option>
                <option>Continuous</option>
                <option>Counter</option>
              </optgroup>
            </select>
          </div>
          <div className="col form-group">
            <label>Attribute</label>
            <select className="form-control" {...register("attribute")}>
              <option></option>
              <option>DARK</option>
              <option>DIVINE</option>
              <option>EARTH</option>
              <option>FIRE</option>
              <option>LIGHT</option>
              <option>WATER</option>
              <option>WIND</option>
            </select>
          </div>
        </div>
        <div className="row">
          <div className="col form-group">
            <label>Level/Rank/Link</label>
            <select className="form-control" {...register("levelRankLinkVal")}>
              <option></option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
              <option>7</option>
              <option>8</option>
              <option>9</option>
              <option>10</option>
              <option>11</option>
              <option>12</option>
              <option>13</option>
            </select>
          </div>
          <div className="col form-group">
            <label>Scale</label>
            <select className="form-control" {...register("scale")}>
              <option></option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
              <option>7</option>
              <option>8</option>
              <option>9</option>
              <option>10</option>
              <option>11</option>
              <option>12</option>
              <option>13</option>
            </select>
          </div>
        </div>
        <div className="row">
          <div className="col form-group">
          <label>Attack</label>
            <input type="number" className="form-control" {...register("atk", { pattern: /[0-9]*/ })}/>
          </div>
          <div className="col form-group">
          <label>Defense</label>
            <input type="number" className="form-control" {...register("def", { pattern: /[0-9]*/ })}/>
          </div>
        </div>
        <div className="row">
          <label>Link Markers</label>
        </div>
        <div className="row text-center">
          <div className="form-group">
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.topLeft")}/>
            </div>
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.top")}/>
            </div>
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.topRight")}/>
            </div>
          </div>
        </div>
        <div className="row text-center">
          <div className="form-group">
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.left")}/>
            </div>
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" disabled/>
            </div>
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.right")}/>
            </div>
          </div>
        </div>
        <div className="row text-center">
          <div className="form-group">
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.bottomLeft")}/>
            </div>
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.bottom")}/>
            </div>
            <div className="form-check form-check-inline">
              <input className="col-4 form-check-input" type="checkbox" {...register("linkMarkers.bottomRight")}/>
            </div>
          </div>
        </div>
        <div className="row">
          <button type="submit" className="btn btn-primary mb-2">Search</button>
          <button type="reset" className="btn btn-danger mb-2">Reset</button>
        </div>
      </form>
    </div>
   )
}


export default CardSearchForm