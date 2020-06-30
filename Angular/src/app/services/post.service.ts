import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  public stateChange: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private url = 'http://localhost:8080/DA_Project/customers';
  private SOAPurl = 'http://localhost:8080/DA_Project/QuoteSoapServiceService';

  constructor(private http: HttpClient) {
  }

  send(body) {
    let headers = new HttpHeaders();
    headers = headers.set('content-type', 'application/json');
    return this.http.post(this.url, body, {headers});
  }

  requestSOAP() {
    let headers = new HttpHeaders();
    headers = headers.set('content-type', 'text/xml');
    const body = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://SOAPwebservices.dev/">\n' +
      '   <soapenv:Header/>\n' +
      '   <soapenv:Body>\n' +
      '      <soap:getSpreuk/>\n' +
      '   </soapenv:Body>\n' +
      '</soapenv:Envelope>';
    return this.http.post(this.SOAPurl, body, {headers, responseType: 'text'});
  }
}
