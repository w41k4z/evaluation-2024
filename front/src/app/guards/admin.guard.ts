import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { ProfilService } from '../service/profil/profil.service';

export const adminGuard: CanActivateFn = () => {
  const profilService = inject(ProfilService);
  const router = inject(Router);

  if (!profilService.isBTP()) {
    router.navigateByUrl('/page/log-in');
    return false;
  }
  return true;
};
