# COMP 433 - Assignment 3 Implementation Questions

## 📁 File Structure

```
├── Q1_Custom_CNN_Fingers.ipynb    # Question 1: Custom CNN (15 points)
├── Q2_ResNet18_Fingers.ipynb       # Question 2: ResNet18 comparison (10 points)
├── Q3_LSTM_TimeSeries.ipynb        # Question 3: LSTM forecasting (20 points)
├── Q4_Transformer_TimeSeries.ipynb # Question 4: Transformer forecasting (15 points)
└── README.md                       # This file
```

## 🚀 Quick Start

### 1. Install Dependencies

```bash
pip install torch torchvision numpy pandas matplotlib seaborn scikit-learn pillow
```

### 2. Setup Datasets

#### For Questions 1 & 2 (Fingers Dataset):
1. Download from: https://www.kaggle.com/datasets/koryakinp/fingers
2. Extract to a folder named `fingers/` in the same directory as notebooks
3. Structure should be:
   ```
   fingers/
   ├── train/
   │   ├── uuid_0L.png, uuid_0R.png, ...
   │   └── (18,000 images total)
   └── test/
       ├── uuid_0L.png, uuid_0R.png, ...
       └── (3,600 images total)
   ```

#### For Questions 3 & 4 (Climate Dataset):
1. Download from: https://www.kaggle.com/datasets/sumanthvrao/daily-climate-time-series-data
2. Place `DailyDelhiClimateTrain.csv` in the same directory as notebooks

### 3. Run Notebooks

**Important Order:**
1. Run **Q1** first → Q2 uses Q1's results for comparison
2. Run **Q3** first → Q4 uses Q3's results for comparison

## 📊 Output Files Generated

### Question 1 Outputs (for PDF report):
| File | Description |
|------|-------------|
| `q1_sample_images.png` | Sample training images |
| `q1_training_curves.png` | Training/validation accuracy & loss |
| `q1_confusion_matrix.png` | Test set confusion matrix |
| `q1_sample_predictions.png` | Sample predictions with labels |
| `best_custom_cnn.pth` | Saved model weights |

### Question 2 Outputs:
| File | Description |
|------|-------------|
| `q2_resnet18_training_curves.png` | ResNet18 training curves |
| `q2_resnet18_confusion_matrix.png` | Confusion matrix |
| `q2_comparison.png` | Custom CNN vs ResNet18 comparison |
| `best_resnet18.pth` | Saved ResNet18 weights |

### Question 3 Outputs:
| File | Description |
|------|-------------|
| `q3_original_data.png` | Original temperature data plot |
| `q3_training_loss.png` | LSTM training loss curve |
| `q3_actual_vs_predicted.png` | Full prediction comparison |
| `q3_zoomed_prediction.png` | Zoomed view (first 200 days) |
| `lstm_model.pth` | Saved LSTM weights |

### Question 4 Outputs:
| File | Description |
|------|-------------|
| `q4_transformer_training_loss.png` | Transformer training loss |
| `q4_transformer_actual_vs_predicted.png` | Full predictions |
| `q4_transformer_zoomed_prediction.png` | Zoomed view |
| `q4_loss_comparison.png` | LSTM vs Transformer loss |
| `q4_prediction_comparison.png` | LSTM vs Transformer predictions |
| `transformer_model.pth` | Saved Transformer weights |

## 🏗️ Model Architectures

### Q1: Custom CNN
```
Input (1×128×128)
    ↓
Conv Block 1 (32 filters) → 32×64×64
    ↓
Conv Block 2 (64 filters) → 64×32×32
    ↓
Conv Block 3 (128 filters) → 128×16×16
    ↓
Conv Block 4 (256 filters) → 256×8×8
    ↓
Flatten → 16384
    ↓
FC + ReLU + Dropout(0.5) → 512
    ↓
FC (Output) → 6 classes
```

### Q2: ResNet18
- PyTorch pre-defined ResNet18
- Trained from scratch (weights=None)
- Only last FC layer modified: 1000 → 6 classes

### Q3: LSTM
```
Input (seq_len=10, features=1)
    ↓
LSTM (2 layers, hidden=64, dropout=0.2)
    ↓
FC → 1 (temperature prediction)
```

### Q4: Transformer
```
Input (seq_len=10, features=1)
    ↓
Input Projection → d_model=64
    ↓
Positional Encoding
    ↓
Transformer Encoder (2 layers, 4 heads)
    ↓
Output Projection → 1 (temperature prediction)
```

## ⚙️ Hyperparameters

### Q1 & Q2 (CNN/ResNet):
| Parameter | Value |
|-----------|-------|
| Learning Rate | 0.001 |
| Batch Size | 64 |
| Epochs | 25 |
| Optimizer | Adam |
| Weight Decay | 1e-4 |
| Dropout | 0.5 |

### Q3 & Q4 (LSTM/Transformer):
| Parameter | Value |
|-----------|-------|
| Learning Rate | 0.001 |
| Batch Size | 32 |
| Epochs | 100 |
| Sequence Length | 10 |
| Optimizer | Adam |
| Loss | MSE |

## 📝 Notes

1. **File Naming Convention** (Fingers dataset):
   - Format: `uuid_XY.png` where X=finger count (0-5), Y=L/R (hand)
   - Example: `abc123_3R.png` = 3 fingers, right hand

2. **Data Preprocessing**:
   - Q1/Q2: Images resized, normalized, augmented
   - Q3/Q4: MinMaxScaler applied, sequences of 10 created

3. **Reproducibility**: Random seeds are set (42) for reproducibility

4. **GPU Support**: Automatically uses CUDA if available
