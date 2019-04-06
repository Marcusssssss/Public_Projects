#pragma once
#include "RepoStars.h"
#include <QAbstractTableModel>

class StarsModelView : public QAbstractTableModel
{
	Q_OBJECT

public:
	StarsModelView(RepoStars& r, QObject *parent);
	~StarsModelView();
	int columnCount(const QModelIndex &parent = QModelIndex()) const override;
	int rowCount(const QModelIndex &parent = QModelIndex()) const override;
	QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const override;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const override;
private:
	RepoStars & stars;
};
