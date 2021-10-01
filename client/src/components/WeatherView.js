import React from 'react';
import ReactDOM from 'react-dom';
import ReactUploadFile from 'react-upload-file';
import { withRouter } from 'react-router-dom'

import weatherConditions from '../util/WeatherConditions.js';

/**
  * Weather view
  *
  * The default view when no alarm is incurred
  *
  * Properties:
  * @param apiUrl
  */
class WeatherView extends React.Component {

  state = {
    weather: null,
    apiUrl: this.props.apiUrl
  }

  componentDidMount() {
    this.fetchWeather();
    this.interval = setInterval(() => {
          console.log("Refreshing weather...");
          this.fetchWeather();
        }
    , 10000);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  /**
    * Openflorian API Access:
    * Fetch the current weather
    *
    * Request:
    * -> GET <API_URL>/data/media/all
    * Expected response:
    * -> application/json - PagedSessionList
    */
  fetchWeather = () => {
    fetch(this.state.apiUrl + '/weather/current')
    .then(result => {
      return result.json();
    }).then(this.renderWeather)
  }

  /**
    * Render received PagedSessionList
    */
  renderWeather = (weather) => {
    console.log("Received weather", weather);

    const PRESSURE_STATE = {
      RISING: "wi pressure wi-direction-up-right",
      FALLING: "wi pressure wi-direction-down-right",
      STEADY: "wi pressure wi-direction-right"
      }

    let temperature = Intl.NumberFormat('de-DE', {style: 'decimal', maximumFractionDigits: 2}).format(weather.temperature) +  " °C";
    let humidity = Intl.NumberFormat('de-DE', {style: 'decimal', maximumFractionDigits: 2}).format(weather.humidity) +  " %";
    let pressure = Intl.NumberFormat('de-DE', {style: 'decimal', maximumFractionDigits: 2}).format(weather.pressure) +  " hPa";

    let windSpeed = Intl.NumberFormat('de-DE', {style: 'decimal', maximumFractionDigits: 2}).format(weather.windSpeed) +  " km/h";
    let windDirection = "wi windspeed wi-wind towards-" + weather.windDirection + "-deg";

    const weatherCondition = (conditionCode) => ({
      OPENWEATHERMAP: "wi weather-icon " + weatherConditions.OPENWEATHER_CONDITONS[conditionCode],
      YAHOOWEATHER: "wi weather-icon " + weatherConditions.YAHOOWEATHER_CONDITIONS[conditionCode]
    })

    let weatherHtml =
        <div>
          <div className="weather-box">
            <div>
              <div className={weatherCondition(weather.conditionCode)[weather.source]}></div>
              <div className="temp-humidity-box">
                <div className="temperature-box">
                  <div className="wi wi-thermometer" />&nbsp;
                  <div className="temperature">{temperature}</div>
                </div>
              </div>
              <div className="humidity-box">
                <div className="wi wi-humidity">&nbsp;</div>
                <div className="humidity">{humidity}</div>
              </div>
            </div>
            <div className="pressure-box">
              <div className="wi wi-barometer" />&nbsp;
              <div className="pressure">{pressure}</div>
              <div className={PRESSURE_STATE[weather.pressureState]}/>
            </div>
            <div className="wind-speed-box">
              <div className="wi wi-strong-wind"/>&nbsp;
              <div className="windspeed">{windSpeed}</div>&nbsp;
              <div className={windDirection} />
            </div>
          </div><br/><br/>
          <div className="weather-box-footer">
            <div className="current-time">Last updated:</div>
            <div className="current-time">{new Date(weather.timestamp).toLocaleString()}</div>
          </div>
        </div>
    this.setState({weather: weatherHtml})
    console.log("state", this.state.weather)
  }

  /**
    * Render WeaterView
    */
  render() {
    return(
    <div>
      <div>
        {this.state.weather}
      </div>
      <div className="claim">
        Served by: OpenFlorian
        <ul>
          <li>https://github.com/krausb/openflorian-ui</li>
          <li>https://github.com/krausb/openflorian-master</li>
        </ul>
      </div>
    </div>
    )
  }

}

export default WeatherView
