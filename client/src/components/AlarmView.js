import React from 'react';
import { Map, Marker, Popup, TileLayer } from 'react-leaflet'

/**
  * Alarm view
  *
  * The view when an alarm is incurred
  *
  * Properties:
  * @param apiUrl
  */
class AlarmView extends React.Component {

  state = {
    alarm: null,
    apiUrl: this.props.apiUrl
  }

  componentDidMount() {
    this.fetchAlarm();
  }

  /**
    * Openflorian API Access:
    * Fetch the current incurred alarm
    *
    * Request:
    * -> GET <API_URL>/alarm/current
    * Expected response:
    * -> application/json - Operation
    */
  fetchAlarm = () => {
    fetch(this.state.apiUrl + '/alarm/current')
    .then(result => {
      return result.json();
    }).then(this.renderAlarm)
  }

  /**
    * Render received alarm
    */
  renderAlarm = (alarm) => {
    console.log("Received alarm", alarm);

    let operationType = "operation-inf"
    if(alarm.technicalAssistanceOperation === true) {
      operationType = "operation-ta"
    } else if(alarm.fireOperation === true) {
      operationType = "operation-fire"
    }

    let resourcesHtml = alarm.resources.map((resource) => {
      if(resource.external === false) {
        return (
            <div className="resource">
              {resource.callName}
            </div>
        )
      }
    })

    let mapPosition = [alarm.positionLatitude, alarm.positionLongitude]
    let mapHtml =
        <div>
          <Map center={mapPosition} zoom={15}>
            <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                attribution=""
            />
            <Marker position={mapPosition}>
              <Popup>Einsatz</Popup>
            </Marker>
          </Map>
          <div className="resources">{resourcesHtml}</div>
        </div>

    let alarmHtml =
        <div className={operationType}>
          <div id="alarm-box" className="alarm-box">
            <div className="buzzword">{alarm.buzzword}</div>
            <div className="details">
              <div className="streetBox">
                <div className="data">{alarm.street}</div>
              </div>
              <div className="cityBox">
                <div className="data">{alarm.city}</div>
              </div>
            </div>
            <div className="map-resources">
              {mapHtml}
            </div>
          </div>
        </div>

    this.setState({alarm: alarmHtml})
    console.log("state", this.state.alarm)
  }

  /**
    * Render AlarmView
    */
  render() {
    return(
    <div>
      <div>
        {this.state.alarm}
      </div>
    </div>
    )
  }

}

export default AlarmView
