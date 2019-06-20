import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-container',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
    
  }

  callRestService(){
    
    console.log('angular function called');
    var val = this.http.get('http://localhost:1233/portalcore/getData');
    val.subscribe((response) => console.log(response));
    console.log('after rest call');
    console.log(val);
    
  }

}
