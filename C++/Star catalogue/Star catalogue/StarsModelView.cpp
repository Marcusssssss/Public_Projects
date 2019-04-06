#include "StarsModelView.h"
#include <qfont.h>
#include <qbrush.h>
#include <qcolor.h>

StarsModelView::StarsModelView(RepoStars& s, QObject *parent):stars(s), QAbstractTableModel(parent)
{
}

StarsModelView::~StarsModelView()
{
}

int StarsModelView::columnCount(const QModelIndex & parent) const
{
	return 4;
}

int StarsModelView::rowCount(const QModelIndex & parent) const
{
	return stars.getStars().size() + 1;
}

QVariant StarsModelView::data(const QModelIndex & index, int role) const
{
	int row = index.row(), col = index.column();

	if (row == stars.getStars().size())
		return QVariant();
	
	Star s = stars.getStars()[row];

	if (role == Qt::DisplayRole || role == Qt::EditRole)
	{
		switch (col)
		{
		case 0:
			return QString::fromStdString(s.getName());
		case 1:
			return QString::fromStdString(s.getConstellation());
		case 2:
			return QString::number(s.getLuminosity());
		case 3:
			return QString::number(s.getTemperature());
		default:
			break;
		}
	}

	if (role == Qt::FontRole)
		return QFont("Times", 12, 10, false);

	return QVariant();
}

QVariant StarsModelView::headerData(int section, Qt::Orientation orientation, int role) const
{
	if(orientation==Qt::Horizontal)
		if (role == Qt::DisplayRole || role == Qt::EditRole)
		{
			switch (section)
			{
			case 0:
				return QString("Name");
			case 1:
				return QString("Constellation");
			case 2:
				return QString("Luminosity");
			case 3:
				return QString("Temperature");
			default:
				break;
			}
		}

	if (role == Qt::FontRole)
		return QFont("Times", 16, 14, false);

	return QVariant();
}
