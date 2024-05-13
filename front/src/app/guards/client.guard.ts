import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { ProfilService } from '../service/profil/profil.service';

export const clientGuard: CanActivateFn = () => {
  const profilService = inject(ProfilService);
  const router = inject(Router);

  if (!profilService.isClient()) {
    router.navigateByUrl('/page/admin/sign-in');
    return false;
  }
  return true;
};
