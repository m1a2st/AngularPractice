import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  constructor(private route: Router) {}

  ngOnInit(): void {}

  doSearch(value: string) {
    console.log(value);
    this.route.navigateByUrl(`/search/${value}`);
  }
}
