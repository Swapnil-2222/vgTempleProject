import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { DelegateComponent } from './list/delegate.component';
import { DelegateDetailComponent } from './detail/delegate-detail.component';
import { DelegateUpdateComponent } from './update/delegate-update.component';
import DelegateResolve from './route/delegate-routing-resolve.service';

const delegateRoute: Routes = [
  {
    path: '',
    component: DelegateComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DelegateDetailComponent,
    resolve: {
      delegate: DelegateResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DelegateUpdateComponent,
    resolve: {
      delegate: DelegateResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DelegateUpdateComponent,
    resolve: {
      delegate: DelegateResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default delegateRoute;
