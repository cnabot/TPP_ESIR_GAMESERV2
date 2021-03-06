import './vendor.ts';

import { NgModule, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Ng2Webstorage } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';

import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { TppEsirGameserv2SharedModule, UserRouteAccessService } from './shared';
import { TppEsirGameserv2AppRoutingModule} from './app-routing.module';
import { TppEsirGameserv2HomeModule } from './home/home.module';
import { TppEsirGameserv2AdminModule } from './admin/admin.module';
import { TppEsirGameserv2AccountModule } from './account/account.module';
import { TppEsirGameserv2EntityModule } from './entities/entity.module';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
import { StateStorageService } from './shared/auth/state-storage.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent,
    GamesbarComponent,
    FooterdetailsComponent,
    GamedetailsComponent,
    GameareaComponent
} from './layouts';
import {
    TictactoeComponent,
    BoardtttComponent,
    CelltttComponent,
    DomineeringComponent,
    BoarddComponent,
    CelldComponent,
    Connect4Component,
    Boardc4Component,
    Cellc4Component,
    GameinfoService
 } from './games';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        TppEsirGameserv2AppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        TppEsirGameserv2SharedModule,
        TppEsirGameserv2HomeModule,
        TppEsirGameserv2AdminModule,
        TppEsirGameserv2AccountModule,
        TppEsirGameserv2EntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent,
        GamesbarComponent,
        TictactoeComponent,
        BoardtttComponent,
        CelltttComponent,
        FooterdetailsComponent,
        GamedetailsComponent,
        GameareaComponent,
        DomineeringComponent,
        BoarddComponent,
        CelldComponent,
        Connect4Component,
        Boardc4Component,
        Cellc4Component
    ],
    providers: [
        ProfileService,
        PaginationConfig,
        UserRouteAccessService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [
                StateStorageService,
                Injector
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [
                JhiEventManager
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        },
        GameinfoService
    ],
    bootstrap: [ JhiMainComponent ],
})
export class TppEsirGameserv2AppModule {}
