import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileUploadDragDropComponent } from './file-upload-drag-drop.component';

describe('FileUploadDragDropComponent', () => {
  let component: FileUploadDragDropComponent;
  let fixture: ComponentFixture<FileUploadDragDropComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileUploadDragDropComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileUploadDragDropComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
