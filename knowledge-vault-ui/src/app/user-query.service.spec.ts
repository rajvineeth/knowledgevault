import { TestBed } from '@angular/core/testing';

import { UserQueryService } from './user-query.service';

describe('UserQueryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserQueryService = TestBed.get(UserQueryService);
    expect(service).toBeTruthy();
  });
});
