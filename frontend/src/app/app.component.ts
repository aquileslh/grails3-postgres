import { Component, OnInit} from '@angular/core';
import {BrowserXhr} from "@angular/http";
import { Http, Headers, HttpModule, RequestOptions, BaseRequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import "rxjs/Rx";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  result: any;
  data;

  constructor(private http : Http){ }

  ngOnInit(){

  }

  consultaServer(){
    this.data =
      this.http
      .get("/api/productos")
      .map(result => result.json());
    console.log(this.data);
  }

}
