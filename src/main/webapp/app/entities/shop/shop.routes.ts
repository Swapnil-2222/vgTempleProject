import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ShopComponent } from './list/shop.component';
import { ShopDetailComponent } from './detail/shop-detail.component';
import { ShopUpdateComponent } from './update/shop-update.component';
import ShopResolve from './route/shop-routing-resolve.service';

const shopRoute: Routes = [
  {
    path: '',
    component: ShopComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ShopDetailComponent,
    resolve: {
      shop: ShopResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ShopUpdateComponent,
    resolve: {
      shop: ShopResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ShopUpdateComponent,
    resolve: {
      shop: ShopResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default shopRoute;
