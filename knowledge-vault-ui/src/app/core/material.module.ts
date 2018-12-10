import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    MatButtonModule, MatCardModule, MatDialogModule, MatInputModule, MatTableModule, MatSidenavModule,
    MatToolbarModule, MatMenuModule, MatIconModule, MatProgressSpinnerModule, MatExpansionModule, MatListModule,
    MatGridListModule, MatProgressBarModule, MatFormFieldModule, MatRippleModule 
} from '@angular/material';
import { MatSelectModule } from '@angular/material/select';

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
        MatSidenavModule,
        MatProgressBarModule,
        MatFormFieldModule,
        MatRippleModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatDialogModule
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
        MatProgressSpinnerModule,
        MatGridListModule,
        MatProgressBarModule,
        MatFormFieldModule,
        MatRippleModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatDialogModule
    ],
})
export class CustomMaterialModule { }
