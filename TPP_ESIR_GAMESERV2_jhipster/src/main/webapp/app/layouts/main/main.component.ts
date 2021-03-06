import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { GameinfoService } from '../../games/gameinfo.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-main',
    templateUrl: './main.component.html'
})
export class JhiMainComponent implements OnInit {

    constructor(
        private titleService: Title,
        private router: Router,
        private gameinfoService: GameinfoService,
        private principal: Principal
    ) {}

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'tppEsirGameserv2App';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    ngOnInit() {
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.titleService.setTitle(this.getPageTitle(this.router.routerState.snapshot.root));
            }
        });
    }
}
