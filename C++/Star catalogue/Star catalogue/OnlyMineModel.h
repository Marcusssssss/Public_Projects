#pragma once
#include "RepoStars.h"
#include <QAbstractTableModel>
#include "Astronomer.h"

class OnlyMineModel : public QAbstractTableModel
{
	Q_OBJECT

public:
	OnlyMineModel(Astronomer& a, RepoStars& r, QObject *parent);
	~OnlyMineModel();
	int columnCount(const QModelIndex &parent = QModelIndex()) const override;
	int rowCount(const QModelIndex &parent = QModelIndex()) const override;
	QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const override;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const override;
private:
	RepoStars & stars;
	Astronomer& astro;
};
