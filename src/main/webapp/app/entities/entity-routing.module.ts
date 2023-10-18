import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'activity',
        data: { pageTitle: 'templeProjectApp.activity.home.title' },
        loadChildren: () => import('./activity/activity.routes'),
      },
      {
        path: 'delegate',
        data: { pageTitle: 'templeProjectApp.delegate.home.title' },
        loadChildren: () => import('./delegate/delegate.routes'),
      },
      {
        path: 'shop',
        data: { pageTitle: 'templeProjectApp.shop.home.title' },
        loadChildren: () => import('./shop/shop.routes'),
      },
      {
        path: 'hotel',
        data: { pageTitle: 'templeProjectApp.hotel.home.title' },
        loadChildren: () => import('./hotel/hotel.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
