import { Component, OnInit } from '@angular/core';
import {GenericService} from "../generic.service";
import {Destination} from "../destination";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: string = "";
  username: string;
  destination: string;
  search_dest: string;
  foundDestinations: Destination[];
  topDestinations: Destination[] = [];
  top3: Destination[] = [];
  showTop3: boolean = false;

  constructor(private genericService: GenericService) { }

  ngOnInit() {
    this.user = window.localStorage.getItem('user');
  }

  addBan() {
    this.genericService.addBan(this.username, this.destination).subscribe(_ => {
            this.username = '';
            this.destination = '';
    });
  }

  sendTheNewValue($event: Event) {
    //console.log(this.search_dest);
    this.genericService.getSearchDestinations(this.search_dest).subscribe(r => {
      if(this.search_dest.length > 0)
        this.topDestinations = this.topDestinations.concat(r);
      this.foundDestinations = r
    });
  }

  showMostPopularDests() {
    let dest1 = this.topDestinations.sort((a,b) =>
      this.topDestinations.filter(v => v.destination===a.destination).length
      - this.topDestinations.filter(v => v.destination===b.destination).length
    ).pop();
    console.log(this.topDestinations);
    if(this.topDestinations.length > 0){
      this.top3.push(dest1); // now find the rest
      for(const d of this.topDestinations.reverse())
      {
        if(! this.top3.some(td => td.id === d.id))
          this.top3.push(d);
        if(this.top3.length === 3)
          break;
      }
      this.topDestinations = [];
      this.showTop3 = true;
    }
  }
}
