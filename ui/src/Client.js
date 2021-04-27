function getSummary(cb) {
  return fetch('/api/summary', {
    accept: "application/json"
  })
    .then(checkStatus)
    .then(parseJSON)
    .then(cb);
}

function shorten(long_url) {
  long_url = long_url.replaceAll("/", "");
  if(long_url.slice(0,10) !== "https:papa" && (long_url.slice(0,4) == "http" || long_url.slice(0,5) == "https"))
  {
    return fetch('/'+ long_url, {

    accept: "application/json"
    })
      .then(checkStatus)
      .then(parseJSON);
  }else
  {
      return fetch('/url/error', {
        accept: "application/json"
      })
      .then(checkStatus)
      .then(parseJSON);
  }
}

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(`HTTP Error ${response.statusText}`);
  error.status = response.statusText;
  error.response = response;
  console.log(error); 
  throw error;
}

function parseJSON(response) {
  return response.json();
}

const Client = { getSummary, shorten };
export default Client;
