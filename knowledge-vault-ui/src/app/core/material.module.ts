import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    MatButtonModule, MatCardModule, MatDialogModule, MatInputModule, MatTableModule, MatSidenavModule,
    MatToolbarModule, MatMenuModule, MatIconModule, MatProgressSpinnerModule, MatExpansionModule, MatListModule
} from '@angular/material';
@NgModule({
    imports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatDialogModule,
        MatTableModule,
        MatMenuModule,
        MatIconModule,
        MatExpansionModule,
        MatProgressSpinnerModule,
        MatListModule,
        MatSidenavModule
    ],
    exports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatDialogModule,
        MatTableModule,
        MatMenuModule,
        MatIconModule,
        MatListModule,
        MatSidenavModule,
        MatExpansionModule,
        MatProgressSpinnerModule
    ],
})
export class CustomMaterialModule { }
